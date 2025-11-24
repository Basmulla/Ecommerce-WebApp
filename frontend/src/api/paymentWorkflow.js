import axios from "../utils/axiosInstance";

// FINAL PAYMENT WORKFLOW (pay for order)
export const payForOrder = async (orderId, method) => {
  const res = await axios.post("/api/payment/pay", {
    orderId: orderId,
    method: method
  });

  return res.data;
};
