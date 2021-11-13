CREATE TABLE components_persistence_integers (
  component BINARY(16),
  persistence_key VARCHAR(32),
  persistent INT NOT NULL,
  PRIMARY KEY (component, persistence_key)
);

CREATE TABLE components_persistence_strings (
  component BINARY(16),
  persistence_key VARCHAR(32),
  persistent VARCHAR(64) NOT NULL,
  PRIMARY KEY (component, persistence_key)
);

CREATE TABLE components_persistence_booleans (
  component BINARY(16),
  persistence_key VARCHAR(32),
  persistent BOOLEAN NOT NULL,
  PRIMARY KEY (component, persistence_key)
);

CREATE TABLE components_persistence_json (
  component BINARY(16),
  persistence_key VARCHAR(32),
  persistent BLOB NOT NULL,
  PRIMARY KEY (component, persistence_key)
);