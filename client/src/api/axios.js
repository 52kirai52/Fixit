import axios from 'axios'

const api = axios.create({
    baseURL: 'http://localhost:8080',
})

// 요청 인터셉터 - 모든 요청에 토큰 자동 추가
api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

export default api