import axios from "../utils/axiosInstance";

// CREATE EMPTY ORDER
export const createOrder = async (orderData) => {
  const res = await axios.post("/api/orders/create", orderData);
  return res.data;
};

// ADD ITEM TO ORDER
export const addItemToOrder = async (detail) => {
  const res = await axios.post("/api/orders/add-item", detail);
  return res.data;
};

// CALCULATE ORDER TOTAL
export const calculateOrderTotal = async (orderId) => {
  const res = await axios.put(`/api/orders/calculate-total/${orderId}`);
  return res.data;
};

// UPDATE ORDER STATUS
export const updateOrderStatus = async (orderId, status) => {
  const res = await axios.put(`/api/orders/status/${orderId}?status=${status}`);
  return res.data;
};

// GET FULL ORDER (order + orderDetails + customer info)
export const getFullOrder = async (orderId) => {
  const res = await axios.get(`/api/orders/full/${orderId}`);
  return res.data;
};

// GET ALL ORDERS
export const getAllOrders = async () => {
  const res = await axios.get("/api/orders/all");
  return res.data;
};

// GET ORDERS BY CUSTOMER
export const getOrdersByCustomer = async (customerId) => {
  const res = await axios.get(`/api/orders/customer/${customerId}`);
  return res.data;
};

// GET ORDERS BY STAFF (shipper/processor)
export const getOrdersByStaff = async (staffId) => {
  const res = await axios.get(`/api/orders/staff/${staffId}`);
  return res.data;
};
