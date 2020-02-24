/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.shardingscaling.mysql.binlog;

import org.apache.shardingsphere.shardingscaling.mysql.binlog.packet.binlog.ColumnDef;
import org.apache.shardingsphere.shardingscaling.mysql.binlog.packet.binlog.TableMapEvent;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * Binlog context.
 */
@Data
public class BinlogContext {

    private String fileName;

    private int checksumLength;

    private Map<Long, TableMapEvent> tableMap = new HashMap<>();
    
    /**
     * Cache table map event.
     *
     * @param tableId table id
     * @param tableMapEvent table map event
     */
    public void putTableMapEvent(final long tableId, final TableMapEvent tableMapEvent) {
        tableMap.put(tableId, tableMapEvent);
    }
    
    /**
     * Get table name by table id.
     *
     * @param tableId table id
     * @return table name
     */
    public String getTableName(final long tableId) {
        return tableMap.get(tableId).getTableName();
    }
    
    /**
     * Get schema name by table id.
     *
     * @param tableId table id
     * @return schema name
     */
    public String getSchemaName(final long tableId) {
        return tableMap.get(tableId).getSchemaName();
    }
    
    /**
     * Get column defined by table id.
     *
     * @param tableId table id
     * @return array of column defined
     */
    public ColumnDef[] getColumnDefs(final long tableId) {
        return tableMap.get(tableId).getColumnDefs();
    }
}
