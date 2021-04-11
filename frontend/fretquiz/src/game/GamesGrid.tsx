import React from 'react';
// import { DataGrid } from '@material-ui/data-grid';
import { useSelector } from 'react-redux';
import { selectGameInfos } from './gameSlice';

const columns = [
  { field: 'gameId', headerName: 'ID' },
  { field: 'hostName', headerName: 'Host' },
  { field: 'playerCount', headerName: 'Players' },
  { field: 'state', headerName: 'Status' },
];

export function GamesGrid() {
  const gameInfos = useSelector(selectGameInfos);

  const rows = gameInfos.map(info => {
    return { ...info, id: info.gameId }
  });

  return (
    <div style={{ height: 300 }}>
      {/* <DataGrid columns={columns} rows={rows} pageSize={8} /> */}
    </div>
  );
}
