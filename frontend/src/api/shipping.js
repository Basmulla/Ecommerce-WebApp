import axios from "../utils/axiosInstance";

// ============================================================
// CREATE SHIPPING RECORD
// POST /api/shipping/create
// ============================================================
export const createShipping = async (data) => {
  const res = await axios.post("/api/shipping/create", data);
  return res.data;
};

// ============================================================
// GET SHIPPING BY ORDER ID
// GET /api/shipping/order/{orderId}
// ============================================================
export const getShippingByOrder = async (orderId) => {
  const res = await axios.get(`/api/shipping/order/${orderId}`);
  return res.data;
};

// ============================================================
// UPDATE SHIPPING STATUS
// PUT /api/shipping/status/{orderId}?status=SHIPPED
// ============================================================
export const updateShippingStatus = async (orderId, status) => {
  const res = await axios.put(`/api/shipping/status/${orderId}?status=${status}`);
  return res.data;
};

// ============================================================
// UPDATE TRACKING NUMBER
// PUT /api/shipping/tracking/{orderId}?trackingNum=123ABC
// ============================================================
export const updateTrackingNumber = async (orderId, trackingNum) => {
  const res = await axios.put(
    `/api/shipping/tracking/${orderId}?trackingNum=${trackingNum}`
  );
  return res.data;
};

// ============================================================
// MARK AS DELIVERED
// PUT /api/shipping/deliver/{orderId}?dateDelivered=2024-02-19
// ============================================================
export const markDelivered = async (orderId, dateDelivered) => {
  const res = await axios.put(
    `/api/shipping/deliver/${orderId}?dateDelivered=${dateDelivered}`
  );
  return res.data;
};

// ============================================================
// DELETE SHIPPING RECORD
// DELETE /api/shipping/delete/{id}
// ============================================================
export const deleteShipping = async (id) => {
  const res = await axios.delete(`/api/shipping/delete/${id}`);
  return res.data;
};
