workspace "Quarkus Prototype" "A set of PoC applications" {

    // uncomment if you want to disable the global scope of element identifiers
    //!identifiers hierarchical

    model {
        qp = softwareSystem "Quarkus Prototype" {
            rs = container "Reservation Service" {
                ctrl = component "Controller Layer"
                biz = component "Business Layer"
                infra = component "Infrastructure Layer"
                pers = component "Persistence Layer"
                util = component "Utility Component"

            }

            //        ss.wa -> ss.db "Reads from and writes to"
        }

        ctrl -> biz "Invokes"
        biz -> infra "Invokes"
        biz -> pers "Invokes"

        ctrl -> util "Invokes"
        biz -> util "Invokes"
        biz -> util "Invokes"
        infra -> util "Invokes"
        pers -> util "Invokes"
}

    views {
        systemContext qp {
            include *
            autolayout lr
        }

        component rs {
            include *
            autolayout tb
        }

        styles {
            element "Element" {
                //color #9a28f8
                //stroke #9a28f8
                strokeWidth 7
                shape roundedbox
            }
            element "Person" {
                shape person
            }
            element "Database" {
                shape cylinder
            }
            element "Boundary" {
                strokeWidth 5
            }
            relationship "Relationship" {
                thickness 4
            }
        }
    }

    configuration {
        scope softwaresystem
    }

}