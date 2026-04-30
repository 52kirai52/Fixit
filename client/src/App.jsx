import { useState, useEffect } from 'react'
import Header from './layout/Header'
import Main from './layout/Main'
import Category from './layout/Category'
import { getMe } from './api/auth'

function App() {
  const [user, setUser] = useState(() => {
    const token = localStorage.getItem('token')
    return token ? undefined : null
  })

  useEffect(() => {
    const token = localStorage.getItem('token')
    if (token) {
      getMe()
        .then(res => {
          setUser({ username: res.data.username, shopName: res.data.shopName })
        })
        .catch(() => {
          localStorage.removeItem('token')
          setUser(null)
        })
    }
  }, [])

  return (
    <div style={{ backgroundColor: '#d1d5db', minHeight: '100vh', display: 'flex', justifyContent: 'flex-start', overflow: 'auto' }}>
      <div style={{ 
        display: 'grid', 
        gridTemplateColumns: '180px 1fr', 
        gridTemplateRows: '160px 1fr', 
        gap: '8px',
        width: '1280px',
        height: '780px',
        flexShrink: 0,
        padding: '8px',
        boxSizing: 'border-box'
      }}>
        <Header user={user} setUser={setUser} />
        <Main user={user} />
        <Category user={user} />
      </div>
    </div>
  )
}

export default App