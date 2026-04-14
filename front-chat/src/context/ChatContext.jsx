import { createContext, useContext, useState } from "react";

const ChatContext = createContext();

export const ChatProvider = ({ children }) => {
  // Persist roomId and currentUser in sessionStorage so they survive page refreshes.
  // sessionStorage is cleared when the browser tab is closed (unlike localStorage),
  // which prevents stale data from leaking across sessions.
  const [roomId, setRoomIdState] = useState(
    () => sessionStorage.getItem("roomId") || ""
  );
  const [currentUser, setCurrentUserState] = useState(
    () => sessionStorage.getItem("currentUser") || ""
  );
  const [connected, setConnectedState] = useState(
    () => sessionStorage.getItem("connected") === "true"
  );

  const setRoomId = (id) => {
    sessionStorage.setItem("roomId", id);
    setRoomIdState(id);
  };

  const setCurrentUser = (user) => {
    sessionStorage.setItem("currentUser", user);
    setCurrentUserState(user);
  };

  const setConnected = (val) => {
    sessionStorage.setItem("connected", String(val));
    setConnectedState(val);
  };

  return (
    <ChatContext.Provider
      value={{
        roomId,
        currentUser,
        connected,
        setRoomId,
        setCurrentUser,
        setConnected,
      }}
    >
      {children}
    </ChatContext.Provider>
  );
};

const useChatContext = () => useContext(ChatContext);
export default useChatContext;
