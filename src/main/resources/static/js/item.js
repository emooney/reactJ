var Content = React.createClass({
    getInitialState: function() {
        return {
            data: [],
            newRecord : {
                name : '',
                description : ''
            },
            editRecord : {
                name : '',
                description : '',
                id : ''
            }
        };
    },
    loadRecordsFromServer: function() {
        $.ajax({
            url: '/load',
            type: 'GET',
            success: function (data) {
                this.setState({
                    data: $.parseJSON(data),
                    newRecord : {
                        name : '',
                        description : ''
                    },
                    editRecord : {
                        name : '',
                        description : '',
                        id : ''
                    }
                });
            }.bind(this)
        });
    },
    componentDidMount: function() {
        this.loadRecordsFromServer();
    },
    handleUpdateRecord: function(record) {
        this.setState({
            editRecord : {
                name : record.name,
                description : record.description,
                id : record.id
            }
        });
    },
    handleNewNameChange: function(e) {
        this.setState({
            newRecord : {
                name : e.target.value,
                description : this.state.newRecord.description
            }
        });
    },
    handleNewDescriptionChange: function(e) {
        this.setState({
            newRecord : {
                name : this.state.newRecord.name,
                description : e.target.value
            }
        });
    },
    handleEditNameChange: function(e) {
        this.setState({
            editRecord : {
                name : e.target.value,
                description : this.state.editRecord.description,
                id : this.state.editRecord.id
            }
        });
    },
    handleEditDescriptionChange: function(e) {
        this.setState({
            editRecord : {
                name : this.state.editRecord.name,
                description : e.target.value,
                id : this.state.editRecord.id
            }
        });
    },
    handleSaveSubmit: function(e) {
        e.preventDefault();
        var name = this.state.newRecord.name;
        var description = this.state.newRecord.description;

        if (!name) {
            return;
        }

        $.ajax({
            url: '/save',
            type: 'POST',
            data: {
                name : name,
                description : description
            },
            success: this.loadRecordsFromServer
        });
    },
    handleUpdateSubmit: function(e) {
        e.preventDefault();
        var name = this.state.editRecord.name;
        var id = this.state.editRecord.id;
        var description = this.state.editRecord.description;

        if(!id) {
            this.setState({
                editRecord: {
                    name: '',
                    description: '',
                    id: ''
                }
            });
        }

        if(!name) {
            return;
        }

        $.ajax({
            url: '/update',
            type: 'POST',
            data: {
                name : name,
                description : description,
                id : id
            },
            success: this.loadRecordsFromServer
        });
    },
    render: function() {
        return (
            <div className="container">
                <Table handleDeleteRecord={this.loadRecordsFromServer} handleUpdateRecord={this.handleUpdateRecord} data={this.state.data}/>
                <div className="row">
                    <div className="col-md-6">
                        <div className="form-group">
                            Name <input type="text" value={this.state.newRecord.name} onChange={this.handleNewNameChange} className="form-control" placeholder="Enter Name"/>
                        </div>
                        <div className="form-group">
                            Description <input type="text" value={this.state.newRecord.description} onChange={this.handleNewDescriptionChange} className="form-control" placeholder="Enter Description"/>
                        </div>
                        <button onClick={this.handleSaveSubmit} className="btn btn-default" type="button">New record</button>
                    </div>
                    <div className="col-md-6">
                        <div className="form-group">
                            Name <input type="text" value={this.state.editRecord.name} onChange={this.handleEditNameChange} className="form-control" placeholder="Enter Name"/>
                        </div>
                        <div className="form-group">
                            Description <input type="text" value={this.state.editRecord.description} onChange={this.handleEditDescriptionChange} className="form-control" placeholder="Enter Description"/>
                        </div>
                        <button className="btn btn-default" onClick={this.handleUpdateSubmit} type="button">Update record</button>
                    </div>
                </div>
            </div>
        );
    }
});

var Table = React.createClass({
    handleDeleteRecord: function() {
        this.props.handleDeleteRecord();
    },
    handleUpdateRecord: function(record) {
        this.props.handleUpdateRecord(record);
    },
    render: function() {
        var recordNodes = new Array();
        for(var i = 0; i < this.props.data.length; i++){
            var record = this.props.data[i];
                recordNodes.push(
                    <Record handleUpdateRecord={this.handleUpdateRecord} handleDeleteRecord={this.handleDeleteRecord} description={record.description} id={record.id} name={record.name}  key={record.id}/>
                );
        }

        return (
            <table className="table table-bordered table-hover text-center">
                <tbody>
                    <tr name="header">
                        <th className="text-center">Name</th>
                        <th className="text-center">Description</th>
                        <th className="text-center"></th>
                        <th className="text-center"></th>
                    </tr>
                    {recordNodes}
                </tbody>
            </table>
        );
    }
});

var Record = React.createClass({
    deleteRecord: function() {
        var id = this.props.id;
        $.ajax({
            url: '/remove',
            type: 'POST',
            data: {
                id : id
            },
            success: this.props.handleDeleteRecord
        });
    },
    updateRecord: function() {
        var record = new Object();
        record.name = this.props.name;
        record.description = this.props.description;
        record.id = this.props.id;
        this.props.handleUpdateRecord(record);
    },
    render: function() {
        return (
            <tr>
                <td>{this.props.name}</td>
                <td>{this.props.description}</td>
                <td>
                    <button className="btn btn-info" onClick={this.updateRecord}>Edit</button>
                </td>
                <td>
                    <button className="btn btn-danger" onClick={this.deleteRecord}>Delete</button>
                </td>
            </tr>
        );
    }
});

ReactDOM.render(
    <Content/>,
    document.getElementById("content")
);
