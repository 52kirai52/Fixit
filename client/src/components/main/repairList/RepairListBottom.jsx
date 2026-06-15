import { useState, useEffect } from 'react'
import { getRepairs } from '../../../api/repairs'

function RepairListBottom() {
  const [repairs, setRepairs] = useState([])
  const [allChecked, setAllChecked] = useState(false)
  const [checked, setChecked] = useState([])

  useEffect(() => {
    getRepairs()
      .then(res => setRepairs(res.data))
      .catch(e => console.error(e))
  }, [])

  const handleAllCheck = (e) => {
    setAllChecked(e.target.checked)
    setChecked(e.target.checked ? repairs.map(r => r.id) : [])
  }

  const handleCheck = (id) => {
    setChecked(prev =>
      prev.includes(id) ? prev.filter(v => v !== id) : [...prev, id]
    )
  }

  const columns = [
    { label: '', width: '40px' },
    { label: '접수일시', width: '150px' },
    { label: '고객명', width: '100px' },
    { label: '차량번호', width: '120px' },
    { label: '차종', width: '120px' },
    { label: '상태', width: '100px' },
    { label: '출고일시', width: '150px' },
    { label: '상세보기', width: '80px' },
  ]

  const thStyle = {
    padding: '4px 8px',
    fontSize: '12px',
    fontWeight: 'bold',
    borderBottom: '2px solid #c0d0e0',
    borderRight: '1px solid #c0d0e0',
    backgroundColor: '#ECE9D8',
    textAlign: 'center',
    userSelect: 'none'
  }

  const tdStyle = {
    padding: '4px 8px',
    fontSize: '12px',
    borderBottom: '1px solid #c0d0e0',
    borderRight: '1px solid #c0d0e0',
    textAlign: 'center',
    overflow: 'hidden',
    whiteSpace: 'nowrap',
    textOverflow: 'ellipsis'
  }

  const formatDate = (dateStr) => {
    if (!dateStr) return '-'
    const d = new Date(dateStr)
    return `${d.getFullYear()}-${String(d.getMonth()+1).padStart(2,'0')}-${String(d.getDate()).padStart(2,'0')} ${String(d.getHours()).padStart(2,'0')}:${String(d.getMinutes()).padStart(2,'0')}`
  }

  return (
    <div style={{ height: '540px', overflow: 'auto' }}>
      <table style={{ width: '100%', borderCollapse: 'collapse', tableLayout: 'fixed' }}>
        <thead>
          <tr>
            <th style={{ ...thStyle, width: '40px' }}>
              <input type="checkbox" checked={allChecked} onChange={handleAllCheck} />
            </th>
            {columns.slice(1).map((col, i) => (
              <th key={i} style={{ ...thStyle, width: col.width }}>
                {col.label}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {repairs.map(repair => (
            <tr key={repair.id}>
              <td style={tdStyle}>
                <input type="checkbox" checked={checked.includes(repair.id)} onChange={() => handleCheck(repair.id)} />
              </td>
              <td style={tdStyle}>{formatDate(repair.createdAt)}</td>
              <td style={tdStyle}>{repair.customerName ?? '-'}</td>
              <td style={tdStyle}>{repair.plateNumber ?? '-'}</td>
              <td style={tdStyle}>{repair.modelName ?? '-'}</td>
              <td style={tdStyle}>{repair.status}</td>
              <td style={tdStyle}>{formatDate(repair.closedAt)}</td>
              <td style={tdStyle}>
                <button style={{ fontSize: '11px', cursor: 'pointer' }}>상세</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default RepairListBottom