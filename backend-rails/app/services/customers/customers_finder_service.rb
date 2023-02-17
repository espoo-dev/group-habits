module Customers
  class CustomersFinderService < BaseService
    attr_reader :name, :customer_type

    def initialize(user:, params:)
      super
      @name = params[:name]
      @customer_type = params[:customer_type]
    end

    def call
      customers = CustomerPolicy::Scope.new(user, Item).resolve
                                       .by_name_like(name)
                                       .by_customer_type(customer_type)

      authorize!(CustomerPolicy, :index?, customers)
    end
  end
end
