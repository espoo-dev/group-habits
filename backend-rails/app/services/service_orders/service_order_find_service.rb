module ServiceOrders
  class ServiceOrderFindService < FindService
    def prepare_resource
      ServiceOrderPolicy::Scope.new(user, ServiceOrder).resolve
                               .where(id: params[:id])
                               .includes(%i[items customer])
                               .first
    end
  end
end
