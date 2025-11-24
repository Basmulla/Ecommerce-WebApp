import axios from "../utils/axiosInstance";

// PRODUCTS
export const getAllProducts = () => axios.get("/api/browse/products");
export const getProduct = (id) => axios.get(`/api/browse/products/${id}`);
export const searchProducts = (keyword) =>
  axios.get(`/api/browse/search?keyword=${keyword}`);

// CUSTOMER
export const createCustomer = (data) =>
  axios.post("/api/customers/create", data);

// ORDERS
export const getOrdersByCustomer = (id) =>
  axios.get(`/api/orders/customer/${id}`);
