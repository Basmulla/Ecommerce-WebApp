import axios from "../utils/axiosInstance";

// CREATE ORDER DETAIL (line item)
export const createOrderDetail = async (detail) => {
  const res = await axios.post("/api/order-details/create", detail);
  return res.data;
};

// GET ALL ITEMS IN AN ORDER
export const getOrderItems = async (orderId) => {
  const res = await axios.get(`/api/order-details/order/${orderId}`);
  return res.data;
};

// GET ONE LINE ITEM
export const getOrderDetailById = async (id) => {
  const res = await axios.get(`/api/order-details/${id}`);
  return res.data;
};

// UPDATE LINE ITEM
export const updateOrderDetail = async (id, newData) => {
  const res = await axios.put(`/api/order-details/update/${id}`, newData);
  return res.data;
};

// DELETE LINE ITEM
export const deleteOrderDetail = async (id) => {
  const res = await axios.delete(`/api/order-details/delete/${id}`);
  return res.data;
};
