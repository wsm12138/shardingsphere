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

package org.apache.shardingsphere.shardingproxy.transport.mysql.packet.command.query.binary.close;

import lombok.Getter;
import lombok.ToString;
import org.apache.shardingsphere.shardingproxy.transport.mysql.packet.command.MySQLCommandPacket;
import org.apache.shardingsphere.shardingproxy.transport.mysql.packet.command.MySQLCommandPacketType;
import org.apache.shardingsphere.shardingproxy.transport.mysql.packet.command.query.binary.MySQLBinaryStatementRegistry;
import org.apache.shardingsphere.shardingproxy.transport.mysql.payload.MySQLPacketPayload;

/**
 * COM_STMT_CLOSE command packet for MySQL.
 *
 * @see <a href="https://dev.mysql.com/doc/internals/en/com-stmt-close.html">COM_STMT_CLOSE</a>
 */
@Getter
@ToString
public final class MySQLComStmtClosePacket extends MySQLCommandPacket {
    
    private final int statementId;
    
    public MySQLComStmtClosePacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_STMT_CLOSE);
        statementId = payload.readInt4();
    }
    
    /**
     * Remove cached statement.
     */
    public void removeCachedStatement() {
        MySQLBinaryStatementRegistry.getInstance().remove(statementId);
    }
}
