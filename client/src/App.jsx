import { useAuth } from './hooks/useAuth'
import { useState } from 'react'

import Header from './layouts/Header'
import Main from './layouts/Main'
import Category from './layouts/Category'

function App() {

  const { user, setUser } = useAuth()
  const [activeMenu, setActiveMenu] = useState(null)

  return (
    <div style={{ backgroundColor: '#d1d5db', minHeight: '100vh', overflow: 'auto' }}>
      <div style={{ 
        display: 'grid',
        gridTemplateAreas: `"header main" "category main"`,
        gridTemplateColumns: '180px 1076px',
        gridTemplateRows: '160px 596px',
        gap: '8px',
        padding: '8px',
      }}>
        <Header user={user} setUser={setUser} />
        <Main activeMenu={activeMenu} />
        <Category activeMenu={activeMenu} setActiveMenu={setActiveMenu} />
      </div>
    </div>
  )
}

export default App