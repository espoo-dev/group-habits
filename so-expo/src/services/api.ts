import { REACT_APP_API_URL } from "@env";
import axios from "axios";

const headers = {
  "Accept": "application/json",
  "Content-Type": "application/json",
}

const api = axios.create({
  baseURL: REACT_APP_API_URL,
  headers,
})

export { api };
