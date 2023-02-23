module ServiceOrders
  class ServiceOrdersFinderService < FinderService
    def prepare_resource
      ServiceOrderPolicy::Scope.new(user, ServiceOrder).resolve
                               .by_name_like(params[:name])
                               .includes(%i[items customer])
    end
  end
end
