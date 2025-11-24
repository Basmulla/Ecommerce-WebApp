import axios from "../utils/axiosInstance";

// ================================
// BASE PRODUCT CRUD
// ================================

// CREATE PRODUCT
export const createProduct = async (data) => {
  const res = await axios.post("/api/products/create", data);
  return res.data;
};

// GET ALL PRODUCTS
export const getAllProducts = async () => {
  const res = await axios.get("/api/products/all");
  return res.data;
};

// GET PRODUCT BY ID
export const getProductById = async (id) => {
  const res = await axios.get(`/api/products/${id}`);
  return res.data;
};

// UPDATE PRODUCT
export const updateProduct = async (id, newData) => {
  const res = await axios.put(`/api/products/update/${id}`, newData);
  return res.data;
};

// DELETE PRODUCT
export const deleteProduct = async (id) => {
  const res = await axios.delete(`/api/products/delete/${id}`);
  return res.data;
};

// ================================
// PRODUCT SEARCH + FILTERS
// ================================

// SEARCH BY KEYWORD
export const searchProducts = async (keyword) => {
  const res = await axios.get(`/api/products/search?keyword=${keyword}`);
  return res.data;
};

// GET BY BRAND
export const getProductsByBrand = async (brand) => {
  const res = await axios.get(`/api/products/brand/${brand}`);
  return res.data;
};

// PRICE RANGE
export const getProductsByPriceRange = async (min, max) => {
  const res = await axios.get(`/api/products/price-range?min=${min}&max=${max}`);
  return res.data;
};

// ACTIVE PRODUCTS
export const getActiveProducts = async () => {
  const res = await axios.get("/api/products/active");
  return res.data;
};

// ================================
// CATEGORY BROWSING
// ================================

export const getBooksCategory = async () => {
  const res = await axios.get("/api/products/category/books");
  return res.data;
};

export const getClothingCategory = async () => {
  const res = await axios.get("/api/products/category/clothing");
  return res.data;
};

export const getElectronicsCategory = async () => {
  const res = await axios.get("/api/products/category/electronics");
  return res.data;
};

// ================================
// SUBTYPE CRUD
// ================================

// BOOKS
export const createBook = async (data) => {
  const res = await axios.post("/api/products/books/create", data);
  return res.data;
};

export const getBookById = async (id) => {
  const res = await axios.get(`/api/products/books/${id}`);
  return res.data;
};

// CLOTHING
export const createClothing = async (data) => {
  const res = await axios.post("/api/products/clothing/create", data);
  return res.data;
};

export const getClothingById = async (id) => {
  const res = await axios.get(`/api/products/clothing/${id}`);
  return res.data;
};

// ELECTRONICS
export const createElectronics = async (data) => {
  const res = await axios.post("/api/products/electronics/create", data);
  return res.data;
};

export const getElectronicsById = async (id) => {
  const res = await axios.get(`/api/products/electronics/${id}`);
  return res.data;
};

// ================================
// STAFF PRODUCT LOOKUP
// ================================

export const getProductsByStaff = async (staffId) => {
  const res = await axios.get(`/api/products/staff/${staffId}`);
  return res.data;
};
