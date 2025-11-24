import axios from "../utils/axiosInstance";

// ========================================
// LOGIN (Backend will implement this)
// POST /api/auth/login
// ========================================
export async function login(credentials) {
  // credentials = { email, password }

  const res = await axios.post("/api/auth/login", credentials);

  // backend will send: { token, user }
  const data = res.data;

  // store token + user
  localStorage.setItem("token", data.token);
  localStorage.setItem("user", JSON.stringify(data.user));

  return data.user;
}

// ========================================
// REGISTER (Backend will implement this)
// POST /api/auth/register
// ========================================
export async function register(data) {
  // data = { name, email, password, etc. }

  const res = await axios.post("/api/auth/register", data);
  return res.data; // backend returns created user
}

// ========================================
// AUTH HELPERS
// ========================================
export function getCurrentUser() {
  const userStr = localStorage.getItem("user");
  return userStr ? JSON.parse(userStr) : null;
} 

export function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("user");
}

export function isLoggedIn() {
  return !!localStorage.getItem("token");
}
