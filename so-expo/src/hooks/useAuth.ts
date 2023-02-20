import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";

function useAuth() {
  const { auth, isSignedIn, getToken, logout } = useContext(AuthContext)

  return { auth, isSignedIn, getToken, logout };
}

export { useAuth };
