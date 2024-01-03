import { api } from "./api";

type UserCredentials = {
  email: string;
  password: string;
}

type AuthParams = {
  user: UserCredentials;
}

const auth = async (params: AuthParams) => {
  try {
    const { data } = await api.post('/v1/users/sign_in', params)
    return data;
  } catch (error) {
    console.error('error', error)
  }
}

export { auth };
