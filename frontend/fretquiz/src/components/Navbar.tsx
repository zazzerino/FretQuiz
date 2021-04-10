import React from 'react';
import { AppBar, Toolbar, Typography, Link, IconButton, makeStyles, StylesProvider } from '@material-ui/core';
import { Link as RouterLink } from 'react-router-dom';
import './Navbar.css';

function NavbarItem(props: { path: string, text: string }) {
  const { path, text } = props;

  return (
    <div className="NavbarItem">
      <Link
        component={RouterLink}
        to={path}
        color="inherit"
      >
        {text}
      </Link>
    </div>
  );
}

const useStyles = makeStyles({
  root: {
    '& a': {
      marginLeft: '2rem',
    }
  }
});

export function Navbar() {
  const styles = useStyles();

  return (
    <div className={styles.root}>
      <AppBar position="static">
        <Toolbar>
          <NavbarItem path="/home" text="Home" />
          <NavbarItem path="/login" text="Login" />
          <NavbarItem path="/game" text="Game" />
        </Toolbar>
      </AppBar>
    </div>
  );
}
