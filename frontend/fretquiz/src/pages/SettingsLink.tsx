import React from 'react';
import { useHistory } from 'react-router';

function SettingsLink() {
  const history = useHistory();

  return (
    <div className="SettingsLink">
      <button onClick={() => history.push('/settings')}>
        Change Settings
      </button>
    </div>
  );
}
