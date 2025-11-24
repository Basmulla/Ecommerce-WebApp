import axios from "../utils/axiosInstance";

// GET ALL ACTIVE PRODUCTS
export const browseAllProducts = async () => {
  const res = await axios.get("/api/browse/products");
  return res.data;
};

// GET ONE PRODUCT
export const browseProductById = async (id) => {
  const res = await axios.get(`/api/browse/products/${id}`);
  return res.data;
};

// SEARCH (keyword)
export const browseSearch = async (keyword) => {
  const res = await axios.get(`/api/browse/search?keyword=${keyword}`);
  return res.data;
};

// CATEGORY BROWSING
export const browseBooks = async () => {
  const res = await axios.get("/api/browse/books");
  return res.data;
};

export const browseClothing = async () => {
  const res = await axios.get("/api/browse/clothing");
  return res.data;
};

export const browseElectronics = async () => {
  const res = await axios.get("/api/browse/electronics");
  return res.data;
};
