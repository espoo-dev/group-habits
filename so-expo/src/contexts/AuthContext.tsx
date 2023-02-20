import { createContext, ReactNode, useCallback } from "react";
import { auth as authAPI } from "../services/auth";
type UserCredentials = {
  email: string;
  password: string;
}

type AuthParams = {
  user: UserCredentials;
}

type AuthContextType = {
  auth: (params: AuthParams) => Promise<void>
}

export const AuthContext = createContext({} as AuthContextType)

interface AuthProviderProps {
  children: ReactNode
}

export function AuthProvider({ children }: AuthProviderProps) {
  const auth = useCallback(async (params: AuthParams) => {
    const response = await authAPI(params)
    console.log('response', response)
  }, [])

  return (
    <AuthContext.Provider
      value={{
        auth,
      }}
    >
      {children}
    </AuthContext.Provider>
  )
}
