module ServiceOrders
  class ServiceOrdersFinderService < FinderService
    def prepare_resource
      ServiceOrderPolicy::Scope.new(user, ServiceOrder).resolve
                               .includes(%i[items customer])
    end
  end
end
