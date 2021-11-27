CREATE TABLE components (
  uuid BINARY(16) PRIMARY KEY,
  component_def VARCHAR(32) NOT NULL
);

CREATE TABLE components_relations (
  parent BINARY(16),
  child BINARY(16),
  PRIMARY KEY (parent, child),
  FOREIGN KEY (parent) REFERENCES components (uuid) ON DELETE CASCADE,
  FOREIGN KEY (child) REFERENCES components (uuid) ON DELETE CASCADE,
  CONSTRAINT child_not_parent CHECK (parent != child)
);

CREATE TABLE components_roots (
  child BINARY(16) PRIMARY KEY,
  root_parent BINARY(16) NOT NULL,
  FOREIGN KEY (root_parent) REFERENCES components (uuid) ON DELETE CASCADE,
  FOREIGN KEY (child) REFERENCES components (uuid) ON DELETE CASCADE,
  CONSTRAINT child_not_parent CHECK (root_parent != child)
);