module Customers
  class CustomersFinderService < FinderService
    def prepare_resource
      resource = CustomerPolicy::Scope.new(user, Item).resolve
        .by_name_like(params[:name])
        .by_customer_type(params[:customer_type])
    end
  end
end
