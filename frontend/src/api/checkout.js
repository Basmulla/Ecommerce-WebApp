import axios from "../utils/axiosInstance";

export const checkout = async (customerId, items) => {
  try {
    const response = await axios.post("/api/checkout", {
      customerId: customerId,
      items: items
    });

    return response.data;
  } catch (error) {
    console.error("Checkout failed:", error);
    throw error;
  }
};
