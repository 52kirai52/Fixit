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

  const [activeMenu, setActiveMenu] = useState(null)

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
        gridTemplateColumns: '180px 1076px',
        gridTemplateRows: '160px 596px',
        gap: '8px',
        width: '1280px',
        height: '780px',
        flexShrink: 0,
        padding: '8px',
        boxSizing: 'border-box'
      }}>
        <Header user={user} setUser={setUser} />
        <Main activeMenu={activeMenu} />
        <Category activeMenu={activeMenu} setActiveMenu={setActiveMenu} />
      </div>
    </div>
  )
}

export default App