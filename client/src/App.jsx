import { useState } from 'react'
import Header from './components/Header'
import Main from './components/Main'
import Category from './components/Category'

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const [userInfo, setUserInfo] = useState(null)

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
        <Header setIsLoggedIn={setIsLoggedIn} setUserInfo={setUserInfo} />
        <Main isLoggedIn={isLoggedIn} userInfo={userInfo} />
        <Category isLoggedIn={isLoggedIn} />
      </div>
    </div>
  )
}

export default App