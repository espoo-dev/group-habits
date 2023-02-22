import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";

function useAuth() {
  const { auth, isSignedIn, getToken, loading, logout } = useContext(AuthContext)

  return { auth, isSignedIn, getToken, loading, logout };
}

export { useAuth };
