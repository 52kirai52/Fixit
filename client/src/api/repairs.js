import api from './axios'

export const registerRepair = (data) => {
    return api.post('/api/repairs', data)
}

export const getRepairs = () => {
    return api.get('/api/repairs')
}