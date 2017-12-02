package org.sselab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSource implements Serializable {
  private static final long serialVersionUID = 5334664952663907334L;
  private String url;
  private String username;
  private String password;
  private String driver;
  private String port;
}
