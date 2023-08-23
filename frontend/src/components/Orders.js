import axios from 'axios';
import React, { useEffect, useState } from 'react'
import NavBar from './NavBar';
import OrderItems from './OrderItems';

function Orders() {

  const [orders, setOrders] = useState([]);

  function fetchOrders() {
    const jwtToken = localStorage.getItem("TOKEN");

    if (jwtToken) {
      axios.get(`http://localhost:8080/orders/existing/${jwtToken}`)
        .then((res) => {
          if (res.status === 200) {
            setOrders(res.data);
          }
        })
        .catch((err) => {
          alert(err);
          console.error(err);
        });
    }
    else {
      window.location.href = '/';
    }
  }


  useEffect(() => {
    fetchOrders();

    const interval = setInterval(fetchOrders, 10000);
    return() => clearInterval(interval);
  }, []);


  function onCancel(id, newStatus) {
    axios.put('http://localhost:8080/orders/cancel/' + id, newStatus, {
      headers: {
        'Content-Type': 'text/plain'  // Setting the content type to plain text
      }
    })
      .then((res) => {
        if (res.status === 201) {
          alert("Order cancelled.");
          fetchOrders();
        }
        else
          alert("Error!");
      })
      .catch((error) => {
        console.error("An error occurred:", error);
        alert("Error!");
      });
  }

  console.log(orders);

  return (
    <div>
      <NavBar />
      <br /><br /><center><h3>Your Orders</h3></center><br />
      <div className='container'>
        <table className="table table-hover table-bordered" >
          <thead className="table-dark">
            <tr>
              <th scope="col"></th>
              <th scope="col">Item Name</th>
              <th scope="col" style={{ textAlign: 'center' }}>Order ID</th>
              <th scope="col" style={{ textAlign: 'center' }}>Quantity</th>
              <th scope="col">Shipping Address</th>
              <th scope="col" style={{ textAlign: 'center' }}>Total Cost</th>
              <th scope="col" style={{ textAlign: 'center' }}>Status</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order, index) => (

              <tr key={order.orderID}>

                <td><b>{index + 1}</b></td>
                <td>{order.itemName}</td>
                <td style={{ textAlign: 'center' }}>{order.orderID}</td>
                <td style={{ textAlign: 'center' }}>{order.quantity}</td>
                <td style={{ maxWidth: '200px', wordWrap: 'break-word' }}>{order.shippingAddress}</td>
                <td style={{ textAlign: 'center' }}>{order.totalCost}</td>
                <td style={{ textAlign: 'center' }}>{order.status}</td>
                <td style={{ textAlign: 'center' }}>
                  {order.status !== "CANCELLED" ? <button type="button" name="statusCancel" className="btn btn-danger" onClick={() => onCancel(order.orderID, "CANCELLED")}>Cancel Order</button> : null}
                </td>

              </tr>

            ))}
          </tbody>
        </table>
      </div><br />
      <OrderItems ordersFetch={fetchOrders} />
    </div>
  )
}

export default Orders