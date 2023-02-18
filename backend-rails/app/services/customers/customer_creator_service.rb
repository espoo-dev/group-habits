module Customers
  class CustomerCreatorService < CreatorService
    def prepare_resource
      @params[:customer_type] = Customer.customer_types[params[:customer_type]]

      Customer.new(params_with_user)
    end
  end
end
