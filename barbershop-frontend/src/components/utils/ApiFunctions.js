import axios from "axios"

export const api = axios.create({
    baseURL: "http://localhost:9192"
})

// This function add a new service to the database
export async function addService(serviceType, price) {
    const formData = new FormData()
    formData.append("serviceType", serviceType)
    formData.append("price", price)

    const response = await api.post("/services/add", formData)
    if(response.status == 201){
        return true
    } else {
        return false
    }
}

// This function gets all service types from the database
export async function getServiceType() {
    try {
        const response = await api.get("/services/service-type")
        return response.data
    } catch (error) {
        throw new Error("Error fetching service type")
    }
}

// This function get all services from the database
export async function getAllServices() {
    try {
        const result = await api.get("/services/all-services")
        return result.data
    } catch (error) {
        throw new Error["Error fetching services"]
    }
}