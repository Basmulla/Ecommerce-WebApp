import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { getOrdersByCustomer } from "../api/orders";

export default function Orders() {
  const [orders, setOrders] = useState([]);

  // If no login: use customerId = 1
  const customerId = 1;

  useEffect(() => {
    async function loadOrders() {
      try {
        const data = await getOrdersByCustomer(customerId);
        setOrders(data);
      } catch (err) {
        console.error(err);
      }
    }
    loadOrders();
  }, []);

  return (
    <div>
      <Navbar />
      <h2>Your Orders</h2>

      {orders.length === 0 && <p>No orders found.</p>}

      {orders.map((order) => (
        <div
          key={order.orderID}
          style={{
            border: "1px solid #ccc",
            padding: "10px",
            marginBottom: "10px",
            borderRadius: "6px"
          }}
        >
          <strong>Order #{order.orderID}</strong>
          <p>Total: ${order.orderTotal}</p>
          <p>Status: {order.status}</p>
        </div>
      ))}
    </div>
  );
}
