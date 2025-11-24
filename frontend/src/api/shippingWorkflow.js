import axios from "../utils/axiosInstance";

// ============================================================
// SHIP ORDER WORKFLOW
// POST /api/shipping/ship
// ============================================================
export const shipOrder = async (orderId, courier, trackingNum, deliveryDate) => {
  const res = await axios.post("/api/shipping/ship", {
    orderId: orderId,
    courier: courier,
    trackingNum: trackingNum,
    deliveryDate: deliveryDate
  });

  return res.data;
};
