/* eslint-disable no-unused-vars */
import React, {useEffect, useState} from 'react'
import { getServiceById, updateService } from "../utils/ApiFunctions"
import { Link, useParams } from "react-router-dom"

const EditService = () => {
  const[service, setService] = useState({
    serviceType: "",
    price: ""
})

const[successMessage, setSuccessMessage] = useState("")
const[errorMessage, setErrorMessage] = useState("")
const { serviceId } = useParams()

const handleInputChange = (event) => {
  const { name, value } = event.target
  setService({ ...service, [name]: value })
}

useEffect(() => {
  const fetchRoom = async () => {
    try {
      const serviceData = await getServiceById(serviceId)
      setService(serviceData)
    } catch (error) {
      console.error(error)
    }
  }

  fetchRoom()
}, [serviceId])


const handleSubmit = async (e) => {
  e.preventDefault()

  try {
    const response = await updateService(serviceId, service)
    if (response.status === 200) {
      setSuccessMessage("Service updated successfully!")
      const updatedServiceData = await getServiceById(serviceId)
      setService(updatedServiceData)
      setErrorMessage("")
    } else {
      setErrorMessage("Error updating service!")
    }
  } catch (error) {
    console.error(error)
    setErrorMessage(error.message)
  }
}

  return (
    <div className="container mt-5 mb-5">
			<h3 className="text-center mb-5 mt-5">Edit Service</h3>
			<div className="row justify-content-center">
				<div className="col-md-8 col-lg-6">
					{successMessage && (
						<div className="alert alert-success" role="alert">
							{successMessage}
						</div>
					)}
					{errorMessage && (
						<div className="alert alert-danger" role="alert">
							{errorMessage}
						</div>
					)}
					<form onSubmit={handleSubmit}>
						<div className="mb-3">
							<label htmlFor="serviceType" className="form-label hotel-color">
								Service Type
							</label>
							<input
								type="text"
								className="form-control"
								id="serviceType"
								name="serviceType"
								value={service.serviceType}
								onChange={handleInputChange}
							/>
						</div>
						<div className="mb-3">
							<label htmlFor="price" className="form-label hotel-color">
								Price
							</label>
							<input
								type="number"
								className="form-control"
								id="price"
								name="price"
								value={service.price}
								onChange={handleInputChange}
							/>
						</div>

						<div className="d-grid gap-2 d-md-flex mt-2">
							<Link to={"/existing-services"} className="btn btn-outline-info ml-5">
								back
							</Link>
							<button type="submit" className="btn btn-outline-warning">
								Edit Service
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
  )
}

export default EditService