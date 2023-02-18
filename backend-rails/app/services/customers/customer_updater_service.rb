module Customers
  class CustomerUpdaterService < UpdaterService
    def initialize(user:, params:)
      super
      @params[:customer_type] = Customer.customer_types[params[:customer_type]]
    end

    def resource_class
      Customer
    end
  end
end
