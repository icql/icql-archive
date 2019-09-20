using Dapper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;

namespace Dapperx
{
    public enum DbType
    {
        SqlServer,Oracle//,Mysql
    }

    public class CommonDap : IDisposable
    {
        private IDbConnection _connection;
        private IDbTransaction _transaction;
        private static object _lock = new object();

        public CommonDap(DbType dbtype, string serverhost,string database,string username,string password)
        {
            switch (dbtype)
            {
                case DbType.SqlServer:
                    string sqlserverConnectionString = String.Format("Data Source={0};Initial Catalog={1};User ID={2};Pwd={3};", serverhost, database, username, password);
                    Connection = new SqlConnection(sqlserverConnectionString);
                    break;
                case DbType.Oracle:
                    string oracleConnectionString = String.Format("Data Source=(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST={0})(PORT=1521))(CONNECT_DATA=(SERVICE_NAME={1})));User ID={2};Password={3};", serverhost, database, username, password);
                    Connection = new Oracle.ManagedDataAccess.Client.OracleConnection(oracleConnectionString);
                    break;
            }
        }

        public IDbConnection Connection
        {
            get { return _connection; }
            protected set { _connection = value; }
        }

        public IDbTransaction Transaction
        {
            get { return _transaction; }
            set
            {
                _transaction = value;
                if (_transaction != null)
                    _connection = _transaction.Connection;
            }
        }

        public IDbTransaction BeginTransaction()
        {
            _transaction = _connection.BeginTransaction();
            return _transaction;
        }

        public IDbTransaction BeginTransaction(IsolationLevel isolationLevel)
        {
            _transaction = _connection.BeginTransaction(isolationLevel);
            return _transaction;
        }

        public int Execute(string sql, dynamic param = null, IDbTransaction transaction = null, int? commandTimeout = null, CommandType? commandType = null)
        {
            lock (_lock)
            {
                if (transaction == null)
                    transaction = _transaction;

                OpenConnection();
                try
                {
                    return SqlMapper.Execute(_connection, sql, param, transaction, commandTimeout, commandType);
                }
                finally
                {
                    CloseConnection();
                }
            }
        }

        public IEnumerable<dynamic> Query(string sql, dynamic param = null, IDbTransaction transaction = null, bool buffered = true, int? commandTimeout = null, CommandType? commandType = null)
        {
            lock (_lock)
            {
                if (transaction == null)
                    transaction = _transaction;

                OpenConnection();
                try
                {
                    return SqlMapper.Query(_connection, sql, param, transaction, buffered, commandTimeout, commandType);
                }
                finally
                {
                    if (buffered)
                        CloseConnection();
                }
            }
        }

        public IEnumerable<T> Query<T>(string sql, dynamic param = null, IDbTransaction transaction = null, bool buffered = true, int? commandTimeout = null, CommandType? commandType = null)
        {
            lock (_lock)
            {
                if (transaction == null)
                    transaction = _transaction;

                OpenConnection();
                try
                {
                    return SqlMapper.Query<T>(_connection, sql, param, transaction, buffered, commandTimeout, commandType);
                }
                finally
                {
                    if (buffered)
                        CloseConnection();
                }
            }
        }

        public IEnumerable<TReturn> Query<TFirst, TSecond, TReturn>(string sql, Func<TFirst, TSecond, TReturn> map, dynamic param = null, IDbTransaction transaction = null, bool buffered = true, string splitOn = "Id", int? commandTimeout = null, CommandType? commandType = null)
        {
            if (transaction == null)
                transaction = _transaction;

            OpenConnection();
            try
            {
                return SqlMapper.Query<TFirst, TSecond, TReturn>(_connection, sql, map, param, transaction, buffered, splitOn,
                                                                 commandTimeout, commandType);
            }
            finally
            {
                if (buffered)
                    CloseConnection();
            }
        }

        public IEnumerable<TReturn> Query<TFirst, TSecond, TThird, TFourth, TFifth, TReturn>(string sql, Func<TFirst, TSecond, TThird, TFourth, TFifth, TReturn> map, dynamic param = null, IDbTransaction transaction = null, bool buffered = true, string splitOn = "Id", int? commandTimeout = null, CommandType? commandType = null)
        {
            if (transaction == null)
                transaction = _transaction;

            OpenConnection();
            try
            {
                return SqlMapper.Query<TFirst, TSecond, TThird, TFourth, TFifth, TReturn>(_connection, sql, map, param, transaction, buffered, splitOn,
                                                                 commandTimeout, commandType);
            }
            finally
            {
                if (buffered)
                    CloseConnection();
            }
        }

        public IEnumerable<TReturn> Query<TFirst, TSecond, TThird, TFourth, TReturn>(string sql, Func<TFirst, TSecond, TThird, TFourth, TReturn> map, dynamic param = null, IDbTransaction transaction = null, bool buffered = true, string splitOn = "Id", int? commandTimeout = null, CommandType? commandType = null)
        {
            if (transaction == null)
                transaction = _transaction;

            OpenConnection();
            try
            {
                return SqlMapper.Query<TFirst, TSecond, TThird, TFourth, TReturn>(_connection, sql, map, param, transaction, buffered, splitOn,
                                                                 commandTimeout, commandType);
            }
            finally
            {
                if (buffered)
                    CloseConnection();
            }
        }

        public IEnumerable<TReturn> Query<TFirst, TSecond, TThird, TReturn>(string sql, Func<TFirst, TSecond, TThird, TReturn> map, dynamic param = null, IDbTransaction transaction = null, bool buffered = true, string splitOn = "Id", int? commandTimeout = null, CommandType? commandType = null)
        {
            if (transaction == null)
                transaction = _transaction;

            OpenConnection();
            try
            {
                return SqlMapper.Query<TFirst, TSecond, TThird, TReturn>(_connection, sql, map, param, transaction, buffered, splitOn,
                                                                 commandTimeout, commandType);
            }
            finally
            {
                if (buffered)
                    CloseConnection();
            }
        }

        public SqlMapper.GridReader QueryMultiple(string sql, dynamic param = null, IDbTransaction transaction = null, int? commandTimeout = null, CommandType? commandType = null)
        {
            if (transaction == null)
                transaction = _transaction;

            OpenConnection();

            return SqlMapper.QueryMultiple(_connection, sql, param, transaction, commandTimeout, commandType);
        }

        public void OpenConnection()
        {
            if (_connection != null && _connection.State != ConnectionState.Open)
                _connection.Open();
        }

        public void CloseConnection()
        {
            if (_connection != null)
                _connection.Close();
        }

        public void Dispose()
        {
            if (_transaction != null)
                _transaction.Dispose();

            if (_connection != null)
                _connection.Dispose();

            _transaction = null;
            _connection = null;
        }
    }
}
