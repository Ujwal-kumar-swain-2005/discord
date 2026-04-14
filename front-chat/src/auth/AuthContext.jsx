import { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext();


const decodeToken = (jwt) => {
  if (!jwt) return {};
  try {
    return JSON.parse(atob(jwt.split(".")[1]));
  } catch {
    return {};
  }
};

const isTokenExpired = (jwt) => {
  const payload = decodeToken(jwt);
  if (!payload.exp) return true;

  return payload.exp * 1000 < Date.now();
};

export const AuthProvider = ({ children }) => {
  /**
   * Initialize token from localStorage
   * Also remove if expired
   */
  const [token, setToken] = useState(() => {
    const savedToken = localStorage.getItem("token");

    if (savedToken && isTokenExpired(savedToken)) {
      localStorage.removeItem("token");
      return null;
    }

    return savedToken;
  });

  /**
   * Login function
   */
  const login = (jwt) => {
    localStorage.setItem("token", jwt);
    setToken(jwt);
  };

  /**
   * Logout function
   */
  const logout = () => {
    localStorage.removeItem("token");
    setToken(null);
    window.location.href = "/login"; // redirect
  };

  /**
   * Auto logout when token expires
   */
  useEffect(() => {
    if (!token) return;

    const payload = decodeToken(token);
    if (!payload.exp) return;

    const timeLeft = payload.exp * 1000 - Date.now();

    if (timeLeft <= 0) {
      logout();
    } else {
      const timer = setTimeout(() => {
        logout();
      }, timeLeft);

      return () => clearTimeout(timer);
    }
  }, [token]);

  /**
   * Extract user info
   */
  const payload = decodeToken(token);
  const username = payload.username || "";
  const roles = payload.roles || [];

  const isAdmin = () => roles.includes("ROLE_ADMIN");

  return (
    <AuthContext.Provider
      value={{
        token,
        login,
        logout,
        username,
        roles,
        isAdmin,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

/**
 * Custom hook
 */
export const useAuth = () => useContext(AuthContext);