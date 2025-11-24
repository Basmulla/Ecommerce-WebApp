import axios from "../utils/axiosInstance";

// ============================================================
// STAFF (Supertype)
// ============================================================

// CREATE STAFF
export const createStaff = async (data) => {
  const res = await axios.post("/api/staff/create", data);
  return res.data;
};

// GET ALL STAFF
export const getAllStaff = async () => {
  const res = await axios.get("/api/staff/all");
  return res.data;
};

// GET STAFF BY ID
export const getStaffById = async (id) => {
  const res = await axios.get(`/api/staff/${id}`);
  return res.data;
};

// UPDATE STAFF
export const updateStaff = async (id, newData) => {
  const res = await axios.put(`/api/staff/update/${id}`, newData);
  return res.data;
};

// DELETE STAFF
export const deleteStaff = async (id) => {
  const res = await axios.delete(`/api/staff/delete/${id}`);
  return res.data;
};

// FILTER BY ROLE
export const getStaffByRole = async (role) => {
  const res = await axios.get(`/api/staff/role/${role}`);
  return res.data;
};

// ============================================================
// MANAGER
// ============================================================

export const createManager = async (data) => {
  const res = await axios.post("/api/staff/manager/create", data);
  return res.data;
};

export const getAllManagers = async () => {
  const res = await axios.get("/api/staff/manager/all");
  return res.data;
};

export const getManagerById = async (id) => {
  const res = await axios.get(`/api/staff/manager/${id}`);
  return res.data;
};

// ============================================================
// PROCESSOR
// ============================================================

export const createProcessor = async (data) => {
  const res = await axios.post("/api/staff/processor/create", data);
  return res.data;
};

export const getAllProcessors = async () => {
  const res = await axios.get("/api/staff/processor/all");
  return res.data;
};

export const getProcessorById = async (id) => {
  const res = await axios.get(`/api/staff/processor/${id}`);
  return res.data;
};

// ============================================================
// SHIPPER
// ============================================================

export const createShipper = async (data) => {
  const res = await axios.post("/api/staff/shipper/create", data);
  return res.data;
};

export const getAllShippers = async () => {
  const res = await axios.get("/api/staff/shipper/all");
  return res.data;
};

export const getShipperById = async (id) => {
  const res = await axios.get(`/api/staff/shipper/${id}`);
  return res.data;
};
