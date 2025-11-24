import axios from "../utils/axiosInstance";

// CREATE PAYMENT
export const createPayment = async (paymentData) => {
  const res = await axios.post("/api/payments/create", paymentData);
  return res.data;
};

// GET PAYMENT BY ORDER
export const getPaymentByOrder = async (orderId) => {
  const res = await axios.get(`/api/payments/order/${orderId}`);
  return res.data;
};

// GET PAYMENT BY ID
export const getPaymentById = async (id) => {
  const res = await axios.get(`/api/payments/${id}`);
  return res.data;
};

// UPDATE PAYMENT
export const updatePayment = async (id, newData) => {
  const res = await axios.put(`/api/payments/update/${id}`, newData);
  return res.data;
};

// DELETE PAYMENT
export const deletePayment = async (id) => {
  const res = await axios.delete(`/api/payments/delete/${id}`);
  return res.data;
};
