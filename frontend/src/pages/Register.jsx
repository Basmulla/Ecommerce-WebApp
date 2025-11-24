import { useState } from "react";
import Navbar from "../components/Navbar";
import { register } from "../api/auth";

export default function Register() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: ""
  });

  async function handleSubmit(e) {
    e.preventDefault();

    try {
      await register(form);
      alert("Account created!");
    } catch (err) {
      alert("Registration failed");
      console.error(err);
    }
  }

  return (
    <div>
      <Navbar />
      <h2>Create Account</h2>

      <form onSubmit={handleSubmit}>
        <input
          placeholder="Full Name"
          onChange={(e) => setForm({ ...form, name: e.target.value })}
        /><br />

        <input
          placeholder="Email"
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        /><br />

        <input
          type="password"
          placeholder="Password"
          onChange={(e) => setForm({ ...form, password: e.target.value })}
        /><br />

        <button>Create Account</button>
      </form>
    </div>
  );
}
