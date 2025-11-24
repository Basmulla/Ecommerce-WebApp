import axios from "../utils/axiosInstance";

export const createCustomer = async (customerData) => {
  const response = await axios.post("/api/customers/create", customerData);
  return response.data;
};

export const getAllCustomers = async () => {
  const response = await axios.get("/api/customers/all");
  return response.data;
};

export const getCustomerById = async (id) => {
  const response = await axios.get(`/api/customers/${id}`);
  return response.data;
};

export const updateCustomer = async (id, newData) => {
  const response = await axios.put(`/api/customers/update/${id}`, newData);
  return response.data;
};

export const deleteCustomer = async (id) => {
  const response = await axios.delete(`/api/customers/delete/${id}`);
  return response.data;
};
