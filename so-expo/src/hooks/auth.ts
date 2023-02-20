import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";

function useAuth() {
  const { auth } = useContext(AuthContext)

  return auth;
}

export { useAuth };
