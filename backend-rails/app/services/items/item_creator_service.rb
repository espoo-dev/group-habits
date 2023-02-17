module Items
  class ItemCreatorService < BaseService
    attr_reader :params

    def initialize(user:, params:)
      @user = user
      @params = params.merge(user:)
    end

    def call
      item = Item.new(params)

      authorize!(ItemPolicy, :create?, item)

      item.save!
      item
    end
  end
end
