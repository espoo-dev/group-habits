import { deleteItemAsync, getItemAsync, setItemAsync } from 'expo-secure-store';
import { createContext, ReactNode, useCallback, useState } from "react";
import { auth as authAPI } from "../services/auth";

type UserCredentials = {
  email: string;
  password: string;
}

type AuthParams = {
  user: UserCredentials;
}

type AuthContextType = {
  auth: (params: AuthParams) => Promise<void>;
  isSignedIn: boolean;
  getToken: () => Promise<string | null>;
  loading: boolean;
  logout: () => Promise<void>;
}

export const AuthContext = createContext({} as AuthContextType)

interface AuthProviderProps {
  children: ReactNode;
}

export function AuthProvider({ children }: AuthProviderProps) {
  const [isSignedIn, setIsSignedIn] = useState(false)
  const [loading, setLoading] = useState(false)

  const getToken = async (): Promise<string | null> => {
    return await getItemAsync('auth-token');
  }

  const setToken = async (token: string) => {
    await setItemAsync('auth-token', token)
    setIsSignedIn(true)
  }

  const auth = useCallback(async (params: AuthParams) => {
    setLoading(true)
    try {
      const { user } = await authAPI(params)
      const { authorization } = user;
      setToken(authorization)
    } catch (error) {
      console.error(error)
    }
    setLoading(false)
  }, [])

  const logout = async () => {
    await deleteItemAsync('auth-token')
    setIsSignedIn(false)
  }

  return (
    <AuthContext.Provider
      value={{
        auth,
        isSignedIn,
        getToken,
        loading,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  )
}
