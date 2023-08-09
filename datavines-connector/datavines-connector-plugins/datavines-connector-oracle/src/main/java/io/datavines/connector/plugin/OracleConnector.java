/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.datavines.connector.plugin;

import io.datavines.common.datasource.jdbc.BaseJdbcDataSourceInfo;
import io.datavines.common.datasource.jdbc.JdbcConnectionInfo;
import io.datavines.common.datasource.jdbc.JdbcDataSourceInfoManager;
import io.datavines.common.datasource.jdbc.JdbcExecutorClient;
import io.datavines.common.datasource.jdbc.entity.DatabaseInfo;
import io.datavines.common.datasource.jdbc.utils.JdbcDataSourceUtils;
import io.datavines.common.param.ConnectorResponse;
import io.datavines.common.param.GetDatabasesRequestParam;
import io.datavines.common.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OracleConnector extends JdbcConnector {

    @Override
    public BaseJdbcDataSourceInfo getDatasourceInfo(JdbcConnectionInfo jdbcConnectionInfo) {
        return new OracleDataSourceInfo(jdbcConnectionInfo);
    }

    @Override
    public ResultSet getMetadataColumns(DatabaseMetaData metaData, String catalog, String schema, String tableName, String columnName) throws SQLException {
        return metaData.getColumns(schema, null, tableName, columnName);
    }

    @Override
    public ResultSet getMetadataTables(DatabaseMetaData metaData, String catalog, String schema) throws SQLException {
        //log.
        return metaData.getTables(catalog, schema, null, TABLE_TYPES);
    }

    @Override
    public ResultSet getMetadataDatabases(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery("select username from all_users");
    }




}
