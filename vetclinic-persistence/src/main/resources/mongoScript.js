db.request.save({
    animalId: 1,
    clientEmail: "client email",
    history: [
        {
            header: "header message",
            description: "descr request",
            employeeEmail: "employee email",
            requestDate: "2017-07-20",
            messages: [
                {
                    email: "user email",
                    message: "msg",
                    date: "2017-07-20"
                }
            ]

        }
    ]
});


db.sequence.insert(
    {
        _id: "issueInfo",
        seq: 0
    }
);



// Aggregation func example
db.request.aggregate(
    {$match: {animalId : 1}},
    {$unwind: "$history"},
    {$group: {_id: "$history"}},
    {$sort: {'_id.requestDate' : -1}}
);

// Aggregation func example
db.request.aggregate(
    {$unwind: "$history"},
    {$group: {_id: "$history.messages"}},
    {$match: {'_id.email': "denis@mail.ru"}},
    {$unwind: "$_id"},
    {$group: {_id: "$_id"}},
    {$sort: {'_id.date': -1}}
);


db.request.aggregate(
    {$unwind: "$history"},
    {$group: {_id: "$history.messages", issueId: {$first: "$issueId"}}},
    {$unwind: "$_id"},
    {$match: {'_id.email': "employee@vetclinic.ru"}},
    {$group: {_id: "$_id", issueId: {$first: "$issueId"}}}
);