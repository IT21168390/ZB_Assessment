import React, { useEffect, useState } from 'react'
import axios from 'axios';

export default function OrderItems(props) {

  const userToken = localStorage.getItem("TOKEN");

  const [itemId, setItemId] = useState();
  const [itemName, setItemName] = useState("");
  const [itemPrice, setItemPrice] = useState();
  const [quantity, setQuantity] = useState();
  const [shippingAddress, setShippingAddress] = useState("");

  const [items, setItems] = useState([]);

  function getItemsList() {
    axios.get('http://localhost:8080/items')
      .then(response => {
        setItems(response.data);
        console.log(response.data)
      })
      .catch(error => {
        console.log(error);
      })
  }

  useEffect(()=>{
    getItemsList();
  },[])

  const handleItemChange = (event) => {
    const [selectedItemId, selectedItemName, selectedItemPrice] = event.target.value.split(",");
    setItemId(parseInt(selectedItemId));
    setItemName(selectedItemName);
    setItemPrice(parseInt(selectedItemPrice));
  };

  function onSubmit(e) {
    e.preventDefault();

    const data = {
      itemId: {itemId: itemId, itemName: itemName, price: itemPrice},
      
      quantity: quantity,
      shippingAddress: shippingAddress
    }
    console.log(data)

    axios.post(`http://localhost:8080/orders/new/${userToken}`, data)
      .then((res) => {
        if (res.status === 201) {
          alert("Order added successfully.");
          props.ordersFetch();
          //window.location.reload();
        }
      })
      .catch((err) => {
        alert(err);
        console.error(err);
      });
  }

  return (
    <div>
      <br /><br /><center><h3><u>Place New Order</u></h3></center>
      <div className="container shadow p-4 mb-5" style={{ margin: 'auto', maxWidth: '75%' }}>
        <div className="row">

          <form onSubmit={onSubmit}>
            <div className="mb-3">
              <select className="form-select" id="selectItem" name="item" required onChange={handleItemChange}>
                <option value="">Select item...</option>
                {items.map((item) => (
                  <option key={item.itemId} value={[item.itemId, item.itemName, item.price]}>{item.itemName}</option>
                ))}
              </select>
            </div>
            <div className="mb-3">
              <label className="form-label">Item Price:</label>
              <input type="text" name="name" className="form-control" value={itemPrice ? itemPrice + " LKR per each" : ""} disabled required />
            </div>
            <div className="mb-3">
              <label className="form-label">Quantity:</label>
              <input type="number" name="quantity" min="1" max="1000" maxLength="4" className="form-control" onChange={(e) => setQuantity(e.target.value)} required />
            </div>
            <div className="mb-3">
              <label className="form-label">Shipping Address:</label>
              <textarea type="text" name="shippingAddress" className="form-control" onChange={(e) => setShippingAddress(e.target.value)} required />
            </div>

            <button type="submit" className="btn btn-primary">Submit</button>
          </form>

        </div>
      </div>

    </div>
  )
}
